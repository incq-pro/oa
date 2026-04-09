# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

A Spring Boot OA (Office Automation) system with MyBatis + SQLite. Supports user management, department management, approval workflows, notice publishing, scheduling, attendance tracking, and CRM (customer/product/sales order management).

## Build & Run

```bash
# Build
mvn clean package

# Run
java -jar target/oa-system-1.0.0.jar

# Default login: admin / 123456
```

Server runs on port 8080. SQLite database file: `./oa.db`

## Architecture

Standard layered architecture: **Controller -> Service -> Mapper**

| Layer | Package | Notes |
|-------|---------|-------|
| Controller | `com.oa.controller` | Return Thymeleaf view names, session-based auth |
| Service | `com.oa.service` | Business logic, injected into controllers |
| Mapper | `com.oa.mapper` | MyBatis interfaces + XML mappers in `resources/mapper/` |
| Entity | `com.oa.entity` | Lombok `@Data`, underscores map to camelCase |

- **Authentication**: Session-based via `LoginInterceptor`. Excluded paths: `/login`, `/login.html`, `/css/**`, `/js/**`
- **Views**: Thymeleaf templates in `src/main/resources/templates/` using `layout.html` as the main layout

## Database Schema

Tables: `department`, `sys_user`, `notice`, `approval_type`, `approval`, `approval_record`, `schedule`, `attendance`, `customer`, `product`, `sales_order`, `sales_order_item`

Schema initialized from `schema.sql` and `data.sql` on startup (mode: always).

## Key Patterns

- **CRUD**: Each entity has Controller + Service + Mapper interface + Mapper XML
- **MyBatis XML**: Located in `src/main/resources/mapper/`, namespace matches mapper interface package
- **Views**: Each module has its own subdirectory under `templates/` (e.g., `user/`, `sales/`, `approval/`)
- **Session user**: Stored as `session.loginUser` (a `User` entity with `deptName` joined field)
