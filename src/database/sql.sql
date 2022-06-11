create table customer
(
    id     int primary key auto_increment,
    name   varchar(20) not null unique,
    wallet double default 0.0
);

create table owner
(
    id          int primary key auto_increment,
    name        varchar(20) not null unique,
    description varchar(255) default null,
    rating      double       default 5.0
);

create table dish
(
    id           int primary key auto_increment,
    name         varchar(20) not null unique,
    introduction varchar(255) default null,
    price        double,
    sales        int          default 0,
    remaining    int          default 0,
    category     int,
    picture      varchar(255),
    owner_id     int         not null,
    foreign key (owner_id) references owner (id)
);

create table orders
(
    id          int primary key auto_increment,
    customer_id int not null,
    owner_id    int not null,
    total       double   default 0.0,
    order_time  datetime default now(),
    completed   boolean  default false,
    cooked      boolean  default false,
    foreign key (owner_id) references owner (id) on delete cascade,
    foreign key (customer_id) references customer (id) on delete cascade
);

create table order_dish
(
    orders_id int,
    dish_id   int,
    amount    int,
    foreign key (orders_id) references orders (id) on delete cascade,
    foreign key (dish_id) references dish (id) on delete cascade
);

create table comments
(
    id          int primary key auto_increment,
    customer_id int          not null,
    owner_id    int          not null,
    content     varchar(255) not null,
    rating      double default 5.0,
    foreign key (customer_id) references customer (id) on delete cascade,
    foreign key (owner_id) references owner (id) on delete cascade
);