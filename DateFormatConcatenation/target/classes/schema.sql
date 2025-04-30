CREATE TABLE IF NOT EXISTS loan_emi_schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    loan_id VARCHAR(255),
    month_number INT,
    emi DOUBLE
);
