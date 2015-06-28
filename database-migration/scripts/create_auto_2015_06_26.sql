CREATE TABLE
    auto
    (
        id SERIAL,
        mark CHARACTER VARYING(50),
        mark_id bigint ,
        
        model CHARACTER VARYING(50),
        model_id bigint ,
        
        body_type CHARACTER VARYING(50),
        body_type_id bigint ,
        
        color CHARACTER VARYING(50),
        color_id bigint ,
        
        driving_gear CHARACTER VARYING(50),
        driving_gear_id bigint ,
        
        engine_volume int NOT NULL,
                        
        fuel CHARACTER VARYING(50),
        fuel_id bigint ,
        
        gear_box_type CHARACTER VARYING(50),
        gear_box_type_id bigint ,
        
        horses int NOT NULL,
        
        power int NOT NULL,
        
        ads_number CHARACTER VARYING(50),
        
        ads_url CHARACTER VARYING(255),
        
        image_url CHARACTER VARYING(255),
        
        price int NOT NULL,
        
        date_time TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
        
        city CHARACTER VARYING(100),
        city_id bigint ,
        
        mile_age int NOT NULL,
        
        year int NOT NULL,
        
        provider CHARACTER VARYING(100),
        provider_id bigint ,
        
        
        PRIMARY KEY (ads_number, provider_id , date_time)
    );