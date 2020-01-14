CREATE TABLE IF NOT EXISTS departments(
    id          SERIAL,
    client_id   UUID NOT NULL,
    name        VARCHAR NOT NULL,
    root_id     INT NOT NULL,
    parent_id   INT
);

INSERT INTO departments (client_id, name, root_id)
VALUES ('00000000-00000000-00000000-00000001', 'Amazing Co', 1);

INSERT INTO departments (client_id, name, root_id, parent_id)
VALUES ('00000000-00000000-00000000-00000002', 'Dept A', 1, 1);

INSERT INTO departments (client_id, name, root_id, parent_id)
VALUES ('00000000-00000000-00000000-00000003', 'Dept B', 1, 1);

INSERT INTO departments (client_id, name, root_id, parent_id)
VALUES ('00000000-00000000-00000000-00000004', 'Dept C', 1, 2);

INSERT INTO departments (client_id, name, root_id, parent_id)
VALUES ('00000000-00000000-00000000-00000005', 'Dept E', 1, 3);

INSERT INTO departments (client_id, name, root_id, parent_id)
VALUES ('00000000-00000000-00000000-00000006', 'Dept F', 1, 3);

INSERT INTO departments (client_id, name, root_id, parent_id)
VALUES ('00000000-00000000-00000000-00000007', 'Dept G', 1, 3);

INSERT INTO departments (client_id, name, root_id, parent_id)
VALUES ('00000000-00000000-00000000-00000008', 'Dept H', 1, 7);
