package com.discountorderapp.scheduling;

import com.discountorderapp.model.Money;
import com.discountorderapp.model.Order;
import com.discountorderapp.report.ReportGenerator;
import com.discountorderapp.service.OrderRepository;
import com.discountorderapp.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("report")
@AllArgsConstructor
public class GenerateReportSchedulerTask {
    private final ReportGenerator reportGenerator;
    private final OrderService orderService;

    @Scheduled(cron = "0 * * * * *")
    public void createRandomOrder() {
        var orders = orderService.getInitOrders();
        reportGenerator.generateReport(orders);
    }

}