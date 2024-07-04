package requests

import org.junit.jupiter.api.Test
import resources.utils.UserUtils

class CreateUserPerformanceTests {
    @Test
    void test_verifyCreateHundredUsersPerformance() {
        //log start time
        long startTime = System.currentTimeMillis()
        //create 100 users
        for(int i=0; i<100; i++) {
            UserUtils.createUserReturnId()
        }
        //log end time
        long endTime = System.currentTimeMillis()
        //calculate elapsed time
        long timeElapsed = endTime - startTime
        println("timeElapsed in miliseconds = " + timeElapsed)
        //verify elapsed time against some threshold
        assert timeElapsed < 1000000
    }
}
