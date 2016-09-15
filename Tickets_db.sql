DROP TABLE if exists activeTechnicians;
DROP TABLE if exists completed;
DROP TABLE if exists added;
DROP TABLE if exists technicians;
DROP TABLE if exists tickets;


CREATE TABLE technicians (
  TechID SERIAL,
  Name varchar(20) NOT NULL,
  Admin boolean NOT NULL,
  Employed boolean NOT NULL,
  PRIMARY KEY (TechID)
);


CREATE TABLE tickets (
  TicketID SERIAL,
  Description text NOT NULL,
  RoomNum varchar(4),
  Reporter varchar(20) NOT NULL,
  PRIMARY KEY (TicketID)
);

CREATE TABLE added (
  TicketID integer,
  AddedDate date NOT NULL,
  AddedBy integer NOT NULL,
  PRIMARY KEY (TicketID),
  FOREIGN KEY (TicketID) REFERENCES tickets (TicketID),
  FOREIGN KEY (AddedBy) REFERENCES technicians (TechID)
);

CREATE TABLE completed (
  TicketID integer,
  CompletedDate date NOT NULL,
  CompletedBY integer NOT NULL,
  PRIMARY KEY (TicketID),
  FOREIGN KEY (TicketID) REFERENCES tickets (TicketID),
  FOREIGN KEY (CompletedBy) REFERENCES technicians (TechID)
);

INSERT INTO technicians (Name, Admin, Employed) VALUES ('postgres', TRUE, TRUE);
