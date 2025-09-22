-- Drop the existing table (if you don't have important data)
DROP TABLE IF EXISTS loans;

-- Create with correct schema
CREATE TABLE IF NOT EXISTS `loans` (
    `loan_id` int NOT NULL AUTO_INCREMENT,
    `mobile_number` varchar(15) NOT NULL,
    `loan_number` varchar(100) NOT NULL,
    `loan_type` varchar(100) NOT NULL,
    `total_loan` int NOT NULL,
    `amount_paid` int NOT NULL,
    `outstanding_amount` int NOT NULL,
    `created_at` datetime NOT NULL,      -- Changed from 'create_at date'
    `created_by` varchar(20) NOT NULL,
    `updated_at` datetime DEFAULT NULL,  -- Allow NULL for new records
    `updated_by` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`loan_id`)
);