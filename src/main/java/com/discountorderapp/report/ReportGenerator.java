package com.discountorderapp.report;

import com.discountorderapp.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

@Component
@Profile("report")
@Slf4j
public class ReportGenerator {

    @Value("${report.path}")
    private String reportPath;

    public void generateReport(List<Order> orders) {
        log.info("Report generation started");
        try {
            Files.createDirectories(Paths.get(reportPath));
            String fileName = "Report_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".pdf";
            String filePath = reportPath + fileName;
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            ClassPathResource imageResource = new ClassPathResource("images/call-report-icon-3.png");
            Image image = Image.getInstance(IOUtils.toByteArray(imageResource.getInputStream()));


            image.scaleToFit(PageSize.A4.getWidth() * 3 / 4, PageSize.A4.getHeight() / 4);
            image.setAlignment(Element.ALIGN_CENTER);
            document.add(image);

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Font fontBody = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

            var title = new Paragraph("Orders report", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            Stream.of("Order No", "Price before discount", "Price after discount")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle, fontBody));
                        table.addCell(header);
                    });

            orders.forEach(order -> {
                table.addCell(new Phrase(order.getOrderCode(), fontBody));
                table.addCell(new Phrase(order.getPriceBeforeDiscount().getAmount().toString(), fontBody));
                table.addCell(new Phrase(order.getPriceAfterDiscount().getAmount().toString(), fontBody));
            });

            document.add(table);
            document.close();

            log.info("Finish of generating report can be found in path: {}", filePath);
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
