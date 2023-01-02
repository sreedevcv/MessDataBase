create table Inmates (
    AdmnNo varchar(10) primary key,
    Name varchar,
    Block char,
    RoomNo int,
    Attendence int default 30
);

create table Workers (
    WId serial primary key,
    Name varchar(30),
    Salary int
);

create table Products (
    PId serial primary key,
    PName varchar(30),
    Price numeric
);

create table Stocks (
    PId int primary key,
    Quantity int,
    foreign key (Pid) references Products (PId)
);

create table Outgoing (
    OUTId serial primary key,
    PId int ,
    OutDate date,
    Quantity int,
    foreign key (Pid) references Products (PId)
);

create or replace trigger outgoingTrigger
after insert
on Outgoing
for each row
execute procedure updateStock();

create or replace function updateStock() returns trigger as $$
declare
    qty int;
begin
    select Quantity 
    into qty
    from Stocks
    where PId = NEW.PId;

    if qty <= NEW.Quantity then
        delete from Stocks
        where PId = NEW.PId;
        raise notice 'hai';
    else
        update Stocks set Quantity = Quantity - NEW.Quantity where PId = NEW.PId;
        raise notice 'hello % %', NEW.Quantity, NEW.PId;
    end if;
    return OLD;
    raise notice 'finished';
end
$$ language plpgsql;

begin;
insert into outgoing values(3, '30-12-22', 5);
rollback;

create or replace function calc( int, varchar) returns float as $$
declare
    TotalCost int;
    SalaryCost int;
    InmateCount int;
    InmateAtt int;
    TotalAtt int;
    x float;
begin

    select sum(y.Pcost) into TotalCost
    from(
        select t.TQ * Products.Price as PCost
        from (
            select sum(Outgoing.Quantity) as TQ, PId
            from Outgoing
            where extract('month' from Outgoing.OutDate) = $1
            group by PId) t
        natural join Products
    ) y;

    select sum(Salary)
    into SalaryCost
    from Workers;

    select count(*)
    into InmateCount
    from Inmates;

    select Attendence
    into InmateAtt
    from Inmates
    where AdmnNo = $2;
    
    select sum(Attendence)
    into TotalAtt
    from Inmates;

    x := ((TotalCost + SalaryCost) / TotalAtt) *InmateAtt;
    raise notice 'x: %, tc: %, sc: %, ta: %, ia: %', x, TotalCost, SalaryCost, TotalAtt, InmateAtt;
    return x;
end
$$ language plpgsql;

select calc(12, '21MH108');

create or replace trigger stockTrigger
before insert
on Stocks
for each row
execute procedure addStocks();

create or replace function addStocks() returns trigger as $$
declare
    existing int;
begin
    select pid
    into existing
    from stocks
    where pid = new.pid;

    if existing is not null then
        update stocks
        set quantity = quantity + new.Quantity
        where pid = new.pid;
        return old;
    else
        return new;
    end if;
end
$$ language plpgsql;

begin;
insert into stocks values(2, 3);

insert into workers (name, salary) values('Sasi', 10000);
insert into workers (name, salary) values('Raghavan', 15000);
insert into workers (name, salary) values('Kumar', 20000);

-- 6 * 150
-- 3 * 40
-- 4 * 35
-- 2 * 20
--

create table MessOut (
    AdmnNo varchar(10),
    month int,
    year int,
    att int,
    PRIMARY KEY(AdmnNo, month, year),
    FOREIGN KEY(AdmnNo) REFERENCES Inmates(AdmnNo)
);


create or replace function newcalc(int, int, varchar) returns float as $$
declare
    TotalCost int;
    SalaryCost int;
    InmateCount int;
    InmateAtt int;
    TotalAtt int;
    x float;
begin

    SELECT sum(t.totalqty * p.price)
    INTO TotalCost
    FROM (
        SELECT sum(quantity) as totalqty, pid
        FROM outgoing
        WHERE extract('month' from outdate) = $1 and extract('year' from outdate) = $2
        GROUP BY pid) t,
        products p
    WHERE t.pid = p.pid;


    select sum(Salary)
    into SalaryCost
    from Workers;

    select count(*)
    into InmateCount
    from Inmates;

    select att
    into InmateAtt
    from MessOut
    where AdmnNo = $3 and month = $1 and year = $2;

    if InmateAtt is null then
        InmateAtt := 30;
    end if;

    select sum(att)
    into TotalAtt
    from MessOut
    where month = $1 and year = $2;

    if TotalAtt is null then
        TotalAtt := InmateCount * 30;
    else
        TotalAtt := InmateCount * 30 - TotalAtt;
    end if;

    x := ((TotalCost + SalaryCost) / TotalAtt) *InmateAtt;
    raise notice 'x: %, tc: %, sc: %, ta: %, ia: %', x, TotalCost, SalaryCost, TotalAtt, InmateAtt;
    return x;
end
$$ language plpgsql;

select newcalc(12, 2022, '21MH108');

begin;
update inmates set attendence = 30 ;
