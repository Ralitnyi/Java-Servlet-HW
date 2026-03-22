package org.homework;


import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {

    private TemplateEngine engine;

    @Override
    public void init() throws ServletException {
        engine = new TemplateEngine();

        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("./src/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(engine.getTemplateResolvers().size());
        resolver.setCacheable(false);
        engine.addTemplateResolver(resolver);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String timezone = req.getParameter("timezone");
        LocalDateTime now;

        if (timezone == null || timezone.isBlank()) {
            Cookie[] cookies = req.getCookies();
            timezone = Arrays.stream(cookies)
                    .filter(c -> c.getName().equals("timezone"))
                    .findFirst().map(Cookie::getValue)
                    .orElse("UTC");
        }

        now = LocalDateTime.now(ZoneId.of(timezone));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Context context = new Context(
                req.getLocale(),
                Map.of(
                        "time", now.format(formatter),
                        "timezone", timezone
                )
        );

        resp.addCookie(new Cookie("timezone", timezone));
        engine.process("time", context, resp.getWriter());
        resp.getWriter().close();
    }
}
