package ekg.utility;

import ekg.entity.ResultsEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataFolderServiceTest {
        DataFolderService dataFolderService = new DataFolderService();

    @Test
    void readDataFileOf() {
        assertDoesNotThrow(()->dataFolderService.readDataFileOf(13L),
                "exception");
    }
    @Test
    void eraseDataOfExistingUserFile() {
        assertDoesNotThrow(() -> dataFolderService.eraseData(13L),
                "exception");
    }
}