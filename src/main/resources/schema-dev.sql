CREATE TABLE IF NOT EXISTS Brands
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Notes
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    group_name  VARCHAR(255) CHECK (group_name IN
                                    ('FLORAL', 'CITRUS', 'GOURMAND', 'WOODY', 'SPICY', 'AQUATIC', 'HERBAL')),
    name        VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Perfumes
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    brand_id     INT,
    release_date DATE,
    type         VARCHAR(20)  NOT NULL,
    price        DOUBLE,
    CONSTRAINT fk_perfumes_brand FOREIGN KEY (brand_id) REFERENCES Brands (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Perfume_Notes
(
    perfume_id INT,
    note_id    INT,
    PRIMARY KEY (perfume_id, note_id),
    CONSTRAINT fk_perfume_notes_perfume FOREIGN KEY (perfume_id) REFERENCES Perfumes (id) ON DELETE CASCADE,
    CONSTRAINT fk_perfume_notes_note FOREIGN KEY (note_id) REFERENCES Notes (id) ON DELETE CASCADE
);
