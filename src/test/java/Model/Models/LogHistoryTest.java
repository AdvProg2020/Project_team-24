package Model.Models;

import Exceptions.LogHistoryDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogHistoryTest {
    @BeforeEach
    void setAccountsToTest() {
        LogHistory logHistory = new LogHistory(100,10,10,null,null);
        logHistory.setLogId(1);
        List<LogHistory> logHistoryList = Arrays.asList(logHistory);
        LogHistory.setList(logHistoryList);
    }

    @Test
    void addLog() {
        LogHistory logHistory = new LogHistory(500,0,40,null,null);
        assertDoesNotThrow(() -> LogHistory.addLog(logHistory));
        assertTrue(LogHistory.getList().contains(logHistory));

    }

    @Test
    void getLogHistoryById1() {
        LogHistory logHistoryexpected = LogHistory.getList().get(0);
        LogHistory logHistoryactusal = assertDoesNotThrow(() -> LogHistory.getLogHistoryById(1));
        assertEquals(logHistoryexpected,logHistoryactusal);

    }
    @Test
    void getLogHistoryById2() {
       assertThrows(LogHistoryDoesNotExistException.class, () -> LogHistory.getLogHistoryById(1000));
    }

    @Test
    void checkExistOfLogHistoryById1() {
        LogHistory logHistory = LogHistory.getList().get(0);
        assertDoesNotThrow(() ->logHistory.checkExistOfLogHistoryById(0,null,null));
    }
    @Test
    void checkExistOfLogHistoryById2() {
        LogHistory logHistory = LogHistory.getList().get(0);
        assertThrows(LogHistoryDoesNotExistException.class,() ->logHistory.checkExistOfLogHistoryById(1000,null,null));
    }

    @Test
    void pack() {
    }

    @Test
    void dpkg() {
    }

}