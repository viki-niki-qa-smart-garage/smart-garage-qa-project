# Smart Garage – Bug Log

This file tracks all defects found during testing of the Smart Garage project.  
Each entry includes a summary table row and a detailed section below.

---

## Bug Summary Table

| ID    | Jira Link | Summary                                      | Severity | Priority | Status   | Found by | Date       |
|-------|-----------|----------------------------------------------|----------|----------|----------|----------|------------|
| BUG-1 | [SG-123](https://your-jira/browse/SG-123) | Currency total incorrect after conversion | Major    | High     | Open     | Niki      | 2025-08-24 |
| BUG-2 | [SG-124](https://your-jira/browse/SG-124) | Forgot password allows empty email         | Minor    | Medium   | Resolved | Viktoria  | 2025-08-24 |

---

## Detailed Entries

### BUG-1: Currency total incorrect after conversion
- **Jira:** [SG-123](https://your-jira/browse/SG-123)  
- **Summary:** Wrong total shown after switching from BGN to EUR on Visit report.  
- **Severity:** Major  
- **Priority:** High  
- **Status:** Open  
- **Found by:** Niki  
- **Environment:** QA env, Chrome 126, Build v1.0.1  

**Steps to Reproduce:**
1. Login as customer (`niki.customer@example.com`).
2. Open *My Services → Visit #123 → Details*.
3. Note the total in BGN.
4. Change currency to EUR.

**Expected:**  
Total_EUR = round2(Total_BGN × rate_shown).

**Actual:**  
Total_EUR differs by 0.25.

**Evidence:**  
- Screenshot before (BGN): `docs/evidence/2025-08-24/bgn.png`  
- Screenshot after (EUR): `docs/evidence/2025-08-24/eur.png`

**Notes:**  
Line items convert correctly, only the grand total is wrong.

---

### BUG-2: Forgot password allows empty email
- **Jira:** [SG-124](https://your-jira/browse/SG-124)  
- **Summary:** Forgot password accepts empty email field.  
- **Severity:** Minor  
- **Priority:** Medium  
- **Status:** Resolved  
- **Found by:** Viktoria  
- **Environment:** QA env, Firefox 128, Build v1.0.1  

**Steps to Reproduce:**
1. Open Forgot Password page.
2. Leave email field blank.
3. Click **Submit**.

**Expected:**  
Validation error: "Email is required".

**Actual:**  
System proceeds with no error.

**Evidence:**  
- Screenshot: `docs/evidence/2025-08-24/forgot-empty.png`

**Notes:**  
Fixed in build v1.0.2.
