🛠 Smart Garage – QA Project

📌 Overview

Smart Garage is a web application designed for auto repair shop owners to manage day-to-day operations efficiently. It serves both employees and customers, providing functionality for:

Vehicle management
Service tracking
Report generation
Email notifications
Loyalty management
The purpose of this test plan is to ensure that all features meet functional, technical, and business requirements through structured manual and automated testing.

🎯 Objectives and Tasks
Objectives:

✅ Ensure all functional requirements are correctly implemented

✅ Validate input, authentication, and authorization mechanisms

✅ Verify filtering, sorting, report generation, PDF creation, currency conversion, and email notifications

✅ Confirm REST API endpoints are functional and documented via Swagger

✅ Identify defects and report them for resolution


Tasks:
📝 Design detailed manual test cases for all functional modules

🏃 Execute test cases and document results

🤖 Create automated suites covering at least the happy paths

🔗 Conduct integration testing for workflows like:
customer registration → service → report generation → email

📊 Prepare test summary and defect reports


📥 Entry Criteria:

Application deployed in test environment

Test data prepared for customers, vehicles, services

REST API deployed with Swagger documentation


📌 Scope

In Scope:

Integration Testing

System Testing:

🧪 Testing Strategy
Integration Testing

Test full workflows:
customer registration → vehicle/service creation → PDF report generation → emails



API Testing:

Verify all main endpoints

Test CRUD operations for core entities: Users, Vehicles, Services

Check status codes and request/response bodies


🛠 Tools:

Jira – Defect tracking, test management

Selenium – Automated regression tests

Postman – REST API testing

Browser developer tools – UI inspection

Docker

Maven

IntelliJ – IDE

GitHub – Source control


🏁 Exit Criteria:

✅ All high-priority test cases executed and documented

✅ 80% of highest-priority test cases executed
