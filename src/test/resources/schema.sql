CREATE TABLE roles(
                      role_id INT PRIMARY KEY,
                      role VARCHAR(255) NOT NULL
);

CREATE TABLE users(
                      user_id SERIAL PRIMARY KEY,
                      first_name VARCHAR(255) NOT NULL,
                      last_name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      address VARCHAR(255) NOT NULL,
                      phone_number VARCHAR(255) NOT NULL,
                      role_fk INT NOT NULL,

                      CONSTRAINT role_fk FOREIGN KEY (role_fk) references roles (role_id)
);

CREATE TABLE orders(
                       order_id SERIAL PRIMARY KEY,
                       total_price FLOAT NOT NULL,
                       user_id_fk INT NOT NULL,

                       CONSTRAINT user_id_fk FOREIGN KEY (user_id_fk) references users (user_id)
);

CREATE TABLE items(
                      item_id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      description VARCHAR(255) NOT NULL,
                      price FLOAT NOT NULL,
                      amount_in_stock INT NOT NULL
);

CREATE TABLE item_groups(
                            item_group_id SERIAL PRIMARY KEY,
                            item_id_fk INT NOT NULL,
                            amount INT NOT NULL,
                            shipping_date DATE NOT NULL,
                            total_price FLOAT NOT NULL,
                            order_id_fk INT NOT NULL,

                            CONSTRAINT item_id_fk FOREIGN KEY (item_id_fk) references items (item_id),
                            CONSTRAINT order_id_fk FOREIGN KEY (order_id_fk) references orders (order_id)
);




