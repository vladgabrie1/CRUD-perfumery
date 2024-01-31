-- Insert data into Brands table
INSERT INTO Brands (name)
VALUES
    ('Chanel'),
    ('Dior'),
    ('Gucci'),
    ('Tom Ford'),
    ('YSL'),
    ('Jo Malone'),
    ('Armani'),
    ('Versace'),
    ('Prada'),
    ('Hermes');

-- Insert data into Notes table
INSERT INTO Notes (name, description, group_name)
VALUES
    ('Rose', 'A captivating floral note with a sweet and enchanting scent that transports you to a blooming garden filled with the essence of romance and beauty.', 'FLORAL'),
    ('Lemon', 'A vibrant citrus note that emanates a burst of freshness and zest, reminiscent of sun-kissed lemon groves on a bright summer day.', 'CITRUS'),
    ('Vanilla', 'A luscious gourmand note featuring the sweet and comforting aroma of vanilla, evoking warmth and indulgence in every whiff.', 'GOURMAND'),
    ('Sandalwood', 'A sophisticated woody note characterized by the warm and velvety fragrance of sandalwood, adding an element of elegance and sensuality.', 'WOODY'),
    ('Pepper', 'A bold and spicy note that brings an exhilarating kick to the fragrance, with a fiery blend that ignites the senses and leaves a lingering allure.', 'SPICY'),
    ('Ocean Breeze', 'An ethereal aquatic note that captures the essence of a cool and refreshing ocean breeze, invoking a sense of serenity and the open sea.', 'AQUATIC'),
    ('Lavender', 'A soothing herbal note with the calming and aromatic properties of lavender, creating a serene and tranquil olfactory experience.', 'HERBAL'),
    ('Jasmine', 'An exotic floral note with a heady and intoxicating aroma, reminiscent of a blossoming jasmine garden in a faraway paradise.', 'FLORAL'),
    ('Bergamot', 'A citrusy note that exudes the uplifting and bright fragrance of bergamot, adding a touch of sophistication and zest to the overall composition.', 'CITRUS'),
    ('Amber', 'A warm and resinous note that envelops the senses with a rich and amber-hued fragrance, creating an aura of opulence and allure.', 'WOODY'),
    ('Oud', 'An exotic woody note that emanates the mysterious and alluring scent of oud, adding depth and intrigue to the perfume with its unique and smoky profile.', 'WOODY'),
    ('Patchouli', 'An earthy note with a deep and grounding aroma of patchouli, infusing the fragrance with a sense of connection to nature and the earth.', 'WOODY'),
    ('Musk', 'A sensual note that radiates with the seductive and musky allure of musk, creating a magnetic and intimate olfactory experience.', 'WOODY'),
    ('Peach', 'A fruity note with the succulent and juicy fragrance of ripe peaches, adding a playful and sweet dimension to the overall composition.', 'GOURMAND'),
    ('Cedar', 'A woody note characterized by the refined and aromatic scent of cedarwood, imparting a sense of strength and sophistication to the fragrance.', 'WOODY'),
    ('Ambergris', 'A sweet and earthy note featuring the captivating aroma of ambergris, adding a touch of mystery and sensuality to the perfume.', 'GOURMAND');

-- Insert data into Perfumes table
INSERT INTO Perfumes (name, brand_id, release_date, type, price)
VALUES
    ('Chanel No. 5', 1, '1921-05-05', 'PERFUME', 100.00),
    ('Dior Sauvage', 2, '2015-08-28', 'PERFUME', 85.00),
    ('Gucci Bloom', 3, '2017-05-02', 'EAU_DE_TOILETTE', 120.00),
    ('Black Orchid', 4, '2006-07-12', 'PERFUME', 150.00),
    ('YSL Black Opium', 5, '2014-08-15', 'EAU_DE_PERFUME', 110.00),
    ('Wood Sage & Sea Salt', 6, '2014-09-01', 'EAU_DE_TOILETTE', 90.00),
    ('Armani Code', 7, '2004-01-01', 'EAU_DE_COLOGNE', 95.00),
    ('Versace Eros', 8, '2012-10-01', 'EAU_FRAICHE', 80.00),
    ('Prada Candy', 9, '2011-08-01', 'PERFUME', 130.00),
    ('Hermes Terre d Hermes', 10, '2006-03-01', 'EAU_DE_PERFUME', 140.00);

INSERT INTO Perfume_Notes (perfume_id, note_id)
VALUES
    (1, 1), (1, 7), (1, 8), (1, 5), (1, 6),
    (2, 2), (2, 4), (2, 5), (2, 6), (2, 7),
    (3, 1), (3, 3), (3, 5), (3, 4), (3, 6),
    (4, 4), (4, 5), (4, 3), (4, 1), (4, 7),
    (5, 5), (5, 7), (5, 3), (5, 1), (5, 8),
    (6, 4), (6, 6), (6, 2), (6, 1), (6, 8),
    (7, 5), (7, 2), (7, 1), (7, 3), (7, 6),
    (8, 6), (8, 1), (8, 3), (8, 4), (8, 5),
    (9, 1), (9, 3), (9, 5), (9, 2), (9, 7),
    (10, 4), (10, 7), (10, 1), (10, 8), (10, 2);
