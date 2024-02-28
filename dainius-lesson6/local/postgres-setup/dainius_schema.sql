create table pets (
  pet_id UUID not null,
  name   varchar(100) not null,
  status char(30) not null,
  created_at timestamp not null,
  modified_at timestamp not null,

  PRIMARY KEY (pet_id)
);

