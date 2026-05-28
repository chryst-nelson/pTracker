DO $$ 
BEGIN
    -- Check if the misspelled column exists and correct one doesn't
    IF EXISTS (SELECT 1 FROM information_schema.columns 
               WHERE table_name='tasks' AND column_name='asignee_id') THEN
        
        -- Drop the foreign key constraint if it exists
        ALTER TABLE tasks DROP CONSTRAINT IF EXISTS fk_task_assignee;
        
        -- Rename the column
        ALTER TABLE tasks RENAME COLUMN asignee_id TO assignee_id;
        
        -- Re-create the foreign key constraint
        ALTER TABLE tasks 
        ADD CONSTRAINT fk_task_assignee 
        FOREIGN KEY (assignee_id) REFERENCES users(id);
    END IF;
END $$;