CREATE SCHEMA eurder;

SET SEARCH_PATH TO eurder;

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

    CONSTRAINT item_id_fk FOREIGN KEY (item_id_fk) references items (item_id)
);

CREATE TABLE orders(
    order_id SERIAL PRIMARY KEY,
    item_groups_orders_fk INT NOT NULL,
    total_price FLOAT NOT NULL,
    customer_id_fk INT NOT NULL,

    CONSTRAINT item_groups_orders_fk FOREIGN KEY (item_groups_orders_fk) references item_groups_orders (item_groups_orders_id),
    CONSTRAINT customer_id_fk FOREIGN KEY (customer_id_fk) references customers (customer_id)
);


CREATE TABLE item_groups_orders(
    item_group_id_fk INTEGER REFERENCES item_groups(item_group_id),
    student_id_fk INTEGER REFERENCES orders(order_id),

    CONSTRAINT item_groups_orders_id PRIMARY KEY(item_group_id_fk, student_id_fk)
);


UUID orderId;
List<ItemGroup> itemGroups;
double totalPrice;
User customer;


UUID id;
String firstName;
String lastName;
String email;
String address;
String phoneNumber;
Role role;


ADMIN,
CUSTOMER