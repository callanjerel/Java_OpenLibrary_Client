/**
 * @author rshirts
 * @createdOn 2/28/2023 at 11:01 AM
 * @projectName LibraryV4
 * @packageName csc150.library.models;
 */
package csc150.library.models;

public enum KeyPossibilities {
    FIRST_PUBLISH_YEAR, AUTHOR_NAME, NUMBER_OF_PAGES_MEDIAN, TITLE, PUBLISHER, SUBJECT;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
