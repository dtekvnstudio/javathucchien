-- V1__init_schema.sql
CREATE TABLE categories (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100)  NOT NULL,
    parent_id   BIGINT        REFERENCES categories(id),
    created_at  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE products (
    id          BIGSERIAL PRIMARY KEY,
    category_id BIGINT          NOT NULL REFERENCES categories(id),
    name        VARCHAR(200)    NOT NULL,
    price       DECIMAL(15, 2)  NOT NULL,
    stock       INTEGER         NOT NULL DEFAULT 0,
    image_url   VARCHAR(500),
    description TEXT,
    created_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_products_category_id ON products(category_id);
CREATE INDEX idx_categories_parent_id ON categories(parent_id);
