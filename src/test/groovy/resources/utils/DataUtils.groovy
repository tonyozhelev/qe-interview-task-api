package resources.utils

import org.apache.commons.lang3.RandomStringUtils

class DataUtils {
    static String chars = 'abcdefghijklmnopqrstuvwxyz'
    static String createRandomName() {
        return RandomStringUtils.random(5,chars)
    }

    static String createRandomUserName() {
        return RandomStringUtils.random(10,chars)
    }

    static String createRandomEmail() {
        return RandomStringUtils.random(10,chars) + "@email.com"
    }

    static String createRandomString(int length) {
        return RandomStringUtils.random(length,chars)
    }
}
