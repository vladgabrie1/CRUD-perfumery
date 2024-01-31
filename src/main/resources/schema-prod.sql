CREATE TABLE IF NOT EXISTS Brands
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Notes
(
    id          SERIAL NOT NULL,
    description VARCHAR(255),
    group_name  VARCHAR(255) CHECK (group_name IN ('FLORAL', 'CITRUS', 'GOURMAND', 'WOODY', 'SPICY', 'AQUATIC', 'HERBAL')),
    name        VARCHAR(255),
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS Perfumes
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    brand_id     INT,
    release_date DATE,
    type         VARCHAR(20)  NOT NULL,
    price        DOUBLE PRECISION,
    CONSTRAINT fk_perfumes_brand FOREIGN KEY (brand_id) REFERENCES Brands (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Perfume_Notes
(
    perfume_id INTEGER,
    note_id    INTEGER,
    PRIMARY KEY (perfume_id, note_id)
);