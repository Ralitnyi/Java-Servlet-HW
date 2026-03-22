# Servlet Time Application

Educational project for demonstrating Java Servlets, Filters, and Cookies functionality.

## Description

This project implements a web application that displays the current time in different time zones. The application uses:
- **Servlets** for handling HTTP requests
- **Filters** for parameter validation
- **Cookies** for storing the selected time zone
- **Thymeleaf** for HTML template rendering

## Features

### Main Capabilities:
- Display current time in `yyyy-MM-dd HH:mm:ss` format
- Support for different time zones via URL parameter `?timezone=UTC%2B2`
- Automatic saving of the last used time zone in cookies
- Time zone validation through filter

### Endpoints:
- `GET /time` - display current time
- `GET /time?timezone=UTC%2B2` - display time for specified zone

## Technologies

- **Java 21**
- **Servlet API 4.0.1**
- **Apache Tomcat 9** (via Maven plugin)
- **Thymeleaf 3.1.3** for templating
- **Maven** for dependency management

## How to Run

1. Make sure you have Maven installed
2. Run the command:
   ```bash
   mvn tomcat9:run
   ```
3. Open your browser at: http://localhost:8080/time

## Usage Examples

### Basic Usage:
```bash
# Current time with default timezone (UTC)
curl http://localhost:8080/time

# Time for specific timezone
curl http://localhost:8080/time?timezone=UTC%2B2
```

### Supported Time Zones:
- `UTC` - Coordinated Universal Time
- `2B` - +
- `UTC%2B2` - UTC+2
- `UTC%2B3` - UTC+3

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── org/homework/
│   │       ├── TimeServlet.java          # Main servlet
│   │       └── filter/
│   │           └── TimezoneValidateFilter.java  # Validation filter
│   └── resources/
└── templates/
    └── time.html                         # HTML template
```

## Components

### TimeServlet
- Handles GET requests to `/time`
- Supports `timezone` parameter
- Uses cookies to store selected zone
- Renders HTML using Thymeleaf

### TimezoneValidateFilter
- Validates `timezone` parameter
- Checks if specified time zone exists
- Returns "Invalid timezone" error for invalid zones

## Development

### For development with hot reload:
The project is configured for automatic reloading when files change:
- `WEB-INF/classes/` - changes in Java classes
- `WEB-INF/lib/` - changes in dependencies
- `WEB-INF/web.xml` - changes in configuration

### Class Structure:
- **Servlet**: `org.homework.TimeServlet`
- **Filter**: `org.homework.filter.TimezoneValidateFilter`
- **Template**: Thymeleaf template in `src/templates/time.html`

## License

Educational project.