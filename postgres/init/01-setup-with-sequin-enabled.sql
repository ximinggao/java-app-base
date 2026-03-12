-- Sequin CDC initialization script
-- This script runs when PostgreSQL container starts for the first time

-- Create sequin database for Sequin's internal state
-- CREATE DATABASE sequin;

-- Grant postgres user access to sequin database
-- GRANT ALL PRIVILEGES ON DATABASE sequin TO postgres;

-- Connect to sequin database and grant schema permissions
-- \connect sequin
-- GRANT ALL ON SCHEMA public TO postgres;

-- Create business databases
CREATE DATABASE account;

-- Connect back to postgres database for replication setup
\connect account

-- CREATE TABLE IF NOT EXISTS public.agents (
--   id BIGSERIAL PRIMARY KEY,
--   name VARCHAR(255) NOT NULL
-- );

-- create or replace function public.update_timestamp()
--   returns trigger
--   language plpgsql
--   as $function$
-- begin
--   if new.updated_at = old.updated_at then
--     new.updated_at = NOW();
-- end if;
-- return NEW;
-- end;
-- $function$;
--
-- create trigger update_timestamp
--     before update on agents for each row
--     execute procedure update_timestamp();

-- Create publication for CDC (Sequin can also create this automatically)
-- This publication captures changes from the agents table
CREATE PUBLICATION sequin_pub FOR ALL TABLES WITH (publish_via_partition_root = true);

-- Note: The replication slot will be created by Sequin automatically
-- when configured with create_if_not_exists: true
select
    pg_create_logical_replication_slot('sequin_slot', 'pgoutput');