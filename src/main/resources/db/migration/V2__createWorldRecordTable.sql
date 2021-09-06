create table world_record
(
    id              bigint auto_increment
        primary key,
    date_of_record  date         null,
    description     varchar(255) null,
    unit_of_measure varchar(255) null,
    value           double       null,
    recorder_id     bigint       null,
    constraint FKn4ssg32wttn2o9vw65cfuub93
        foreign key (recorder_id) references recorder (id)
);