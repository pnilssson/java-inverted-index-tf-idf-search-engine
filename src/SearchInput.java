import java.util.Scanner;

public class SearchInput {
    protected String getSearchInput() {
        printSearch();
        return new Scanner(System.in).nextLine();
    }

    private void printSearch() {
        System.out.print("Search: ");
    }

}
