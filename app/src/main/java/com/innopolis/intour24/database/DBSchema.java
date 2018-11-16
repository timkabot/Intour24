package com.innopolis.intour24.database;

/**
 * Created by ekaterina on 5/24/17.
 * TODO src for pictures
 */

public class DBSchema {
    public static final String DB_NAME = "KAZAN_TOURS";

    /**
     * DB of all available tours
     */
    public static final class Tours {
        public static final String TABLE_NAME = "Tours";

        public static final class Columns {
            public static final String ID = "ID";
            public static final String TOUR_NAME = "TOUR_NAME";
            public static final String DESC = "DESCRIPTION";
            public static final String IS_LUNCH = "IS_LUNCH";
            public static final String TIME = "TIME";
            public static final String CAPACITY = "CAPACITY";
            public static final String DURATION = "DURATION";
            public static final String PRICE = "PRICE";
            public static final String START_PLACE = "START_PLACE";
            public static final String CATEGORY = "CATEGORY";
            public static final String CHILD_PRICE = "CHILD_PRICE";
            public static final String BABY_PRICE = "BABY_PRICE";
            public static final String SCHEDULE = "SCHEDULE";
            public static final String AVERAGE_RATING = "AVERAGE_RATING";
            public static final String COMPANY = "COMPANY";
            public static final String LAT = "LAT";
            public static final String LNG = "LNG";
        }
    }

    /**
     * DB of all available category of tour
     */
    public static final class Category {
        public static final String TABLE_NAME = "Categories";

        public static final class Columns {
            public static final String TOUR_ID = "TOUR_ID";
            public static final String TYPE = "TYPE";
        }
    }

    /**
     * DB of all signs in city
     */
    public static final class SightTable {
        public static final String TABLE_NAME = "Sights";

        public static final class Columns {
            public static final String ID = "ID";
            public static final String DESC = "DESCRIPTION";
            public static final String MAX_PRICE = "MAX_PRICE";
            public static final String MIN_PRICE = "MIN_PRICE";
            public static final String GEOPOSITION = "GEOPOSITION";
            public static final String NAME = "NAME";
            public static final String TOURS_ID = "TOURS_ID";
            public static final String PICTURE_SRC = "PICTURE_SRC";
        }
    }


    /**
     * Many to many relation of db
     * One sign has many excursions
     */
    public static final class RelTableToursSigns {
        public static final String TABLE_NAME = "RelTableToursSigns";

        public static final class Columns  {
            public static final String SIGN_ID = "SIGN_ID";
            public static final String TOUR_ID = "TOUR_ID";
        }
    }

    public static final class SightProperties {
        public static final String TABLE_NAME = "Sight_properties";

        public static final class Columns {
            public static final String SIGN_ID = "SIGN_ID";
            public static final String ICON_SRC = "ICON_SRC";
            public static final String NAME = "NAME";
        }
    }

    public static final class ExcursionProperties {
        public static final String TABLE_NAME = "Excursion_properties";

        public static final class Columns {
            public static final String EXCURSION_ID = "EXCURSION_ID";
            public static final String ICON_SRC = "ICON_SRC";
            public static final String NAME = "NAME";
        }
    }

    public static final class ExcursionImages{
        public static final String TABLE_NAME = "Excursion_Images";

        public static final class Columns{
            public static final String EXCURSION_ID = "EXCURSION_ID";
            public static final String IMG_SRC = "IMG_SRC";
        }
    }

    public static final class UserTable {
        public static final String TABLE_NAME = "User";

        public static final class Columns {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String PHONE = "phone";
        }
    }
}