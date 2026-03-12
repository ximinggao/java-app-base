-- Sequin CDC initialization script
-- This script runs when PostgreSQL container starts for the first time

-- Create sequin database for Sequin's internal state
CREATE DATABASE sequin;

-- Grant demo user access to sequin database
GRANT ALL PRIVILEGES ON DATABASE sequin TO demo;

-- Connect to sequin database and grant schema permissions
\connect sequin
GRANT ALL ON SCHEMA public TO demo;

-- Connect back to demo database for replication setup
\connect account

CREATE TABLE IF NOT EXISTS public.agents (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

-- Create publication for CDC (Sequin can also create this automatically)
-- This publication captures changes from the agents table
CREATE PUBLICATION sequin_pub FOR TABLE public.agents;

-- Note: The replication slot will be created by Sequin automatically
-- when configured with create_if_not_exists: true