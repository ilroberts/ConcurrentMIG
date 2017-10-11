package com.kainos.db;

import org.javatuples.Pair;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.kainos.db.InitDb.getCodeDescriptions;
import static com.kainos.db.InitDb.initialize;
import static com.kainos.db.InitDb.teardown;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InitDbTest {

    @Test
    @Ignore
    public void testInitDb() throws SQLException {

        initialize();
        Optional<List<Pair<String, String>>> result = getCodeDescriptions();
        teardown();
        assertTrue(result.isPresent());
        assertEquals(result.get().size(), 5);
    }
}
