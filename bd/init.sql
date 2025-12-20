
CREATE TABLE agents (
    id_agent INT PRIMARY KEY,
    name_agent VARCHAR(255) NOT NULL, 
    extension INT NOT NULL 
);


CREATE TABLE calls (
    call_id INT PRIMARY KEY, 
    id_agent INT NOT NULL, 
    date_time VARCHAR(100) NOT NULL, 
    download_url VARCHAR(1000), 
    CONSTRAINT fk_agent 
        FOREIGN KEY (id_agent) 
        REFERENCES agents(id_agent) 
        ON DELETE CASCADE
);