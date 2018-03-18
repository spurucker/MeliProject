create table if not exists dna
(
   id bigint auto_increment,
   is_mutant boolean not null,
   dna varchar not null UNIQUE,
   primary key(id)
);