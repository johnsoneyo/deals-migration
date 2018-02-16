/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.services;

import com.bloomberg.deals.dto.Columns;
import com.bloomberg.deals.dto.Row;
import com.bloomberg.deals.dto.StatReport;
import com.bloomberg.deals.dto.entity.DealFile;
import com.bloomberg.deals.dto.entity.InvalidDealFile;
import com.bloomberg.deals.dto.entity.ValidDealFile;
import com.bloomberg.deals.exception.MigrationException;
import com.bloomberg.deals.repository.DealFileRepository;
import com.bloomberg.deals.repository.InvalidFileRepository;
import com.bloomberg.deals.repository.ValidFileRepository;
import com.bloomberg.deals.repository.dao.DealCountDao;
import com.bloomberg.deals.repository.dao.DealFileDao;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service
public class CSVService {

    @Value("${path.deal.file}")
    private String csvFile;
    @Value("${path.deal.store}")
    private String csvFilePath;
    @Autowired
    private RowService rowService;
    @Autowired
    private DealFileRepository dfrepo;
    @Autowired
    private InvalidFileRepository infrepo;
    @Autowired
    private ValidFileRepository vfrepo;
    @Autowired
    private DealFileDao dfao;
     @Autowired
    private DealCountDao dcao;
    private int validCount = 0;
    private int invalidCount = 0;
    private ValidDealFile odf;

    public StatReport processFile(DealFile df) throws IOException, MigrationException {
        boolean skipLine = false;
        String[] nextRecord;
        String[] header = Columns.values;
        String validFile = "valid_".concat(String.valueOf(new Random().nextInt(1000000))).concat(".csv");
        String invalidFile = "invalid_".concat(String.valueOf(new Random().nextInt(1000000))).concat(".csv");
        String validFilePath = csvFilePath.concat(validFile);
        String invalidFilePath = csvFilePath.concat(invalidFile);
        try (
                Reader reader = Files.newBufferedReader(Paths.get(df.getFilePath()));
                CSVReader csvReader = new CSVReader(reader);
                Writer valid = Files.newBufferedWriter(Paths.get(validFilePath));
                Writer invalid = Files.newBufferedWriter(Paths.get(invalidFilePath));
                CSVWriter validw = new CSVWriter(valid,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
                CSVWriter invalidw = new CSVWriter(invalid,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);) {
            validw.writeNext(header);

            while ((nextRecord = csvReader.readNext()) != null) {

                if (!skipLine) {
                    //validate row columns 
                    Row root = rowService.getStructure();
                    Row pointer = root;
                    int c = 0;
                    for (pointer = root; pointer != null; pointer = pointer.next) {
                        if (c == nextRecord.length) {
                            throw new MigrationException("Invalid number of columns");
                        }
                        if (!pointer.name.equals(nextRecord[c])) {
                            throw new MigrationException("Invalid column order");
                        }
                        c++;
                    }
                    skipLine = true;

                }// end skipline

                //process row and write to table/document
                //validate fields
                boolean ben = true;
                for (int d = 0; d < nextRecord.length; d++) {
                    if (nextRecord.length < header.length) {
                        ben &= false;

                    } else if (nextRecord[d].length() == 0 & d != nextRecord.length - 2) {
                        ben &= false;

                    } else if (d == nextRecord.length - 1) {
                        String amount = nextRecord[d];
                        boolean number = StringUtils.isNumericSpace(amount);
                        if (!number) {
                            ben &= false;
                        } else {
                            ben &= true;
                        }
                    } else {
                        ben &= true;
                    }

                }// end for
                if (ben) {
                    validw.writeNext(nextRecord);
                    validCount++;
                } else {
                    invalidw.writeNext(nextRecord);
                    invalidCount++;
                }
            }// end while

            ValidDealFile vdf = new ValidDealFile();
            vdf.setFileName(validFilePath);

            InvalidDealFile idf = new InvalidDealFile();
            idf.setFileName(invalidFilePath);

            df.setInvalidDealFile(idf);
            df.setValidDealFile(vdf);
            vdf.setDealFile(df);
            idf.setDealFile(df);
            dfrepo.save(df);
            odf = vdf;

        }
        dfao.loadFile(odf.getFileName());
        dcao.dealPerOrderingCurrency();

        return new StatReport(null, validCount, invalidCount);

    }

}
