package com.cd.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Font;

@Component
public class PdfGeneratorUtility {

	@Value("${pdfDir}")
	private String pdfDir;
	
	@Value("${reportFileName}")
	private String reportFileName;
	
	@Value("${reportFileNameDateFormat}")
	private String reportFileNameDateFormat;
	
	@Value("${localDateFormat}")
	private String localDateFormat;
	
	@Value("${logoImgPath}")
	private String logoImagePath;
	
	@Value("${logoImgScale}")
	private Float[] logoImgScale;
	@Value("${currencySymbol}")
	private String currencySymbol;
	@Value("${table_noOfColumns}")
	private int noOfColumns;
	@Value("${table.columnNames}")
	private List<String> columnNames;
	
	private static Font COURIER = new Font(Font.FontFamily.COURIER,20,Font.BOLD);
	private static Font COURIER_SMALL = new Font(Font.FontFamily.COURIER, 16, Font.BOLD);
	private static Font COURIER_SMALL_FOOTER = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);
	
	private static final Path root = Paths.get("invoices");
}
