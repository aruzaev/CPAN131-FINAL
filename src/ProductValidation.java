public class ProductValidation {
    private Inventory stock;

    public ProductValidation(Inventory stock) {
        this.stock = stock;
    }

    //Validate ID
    public boolean isValidId(String id) {
        //Checks to see if it is even a number and an integer
        try {
            int value = Integer.parseInt(id);
            if (value > 0) { //make sure it is non-negative
                for (Product product : stock.getProducts()) {
                    if (product.getId() == value) {
                        return false; // ID already exists
                    }
                }
                return true; // ID is unique
            }
        }
        //if the quantity is not even a number it will catch error so the program does not crash
        //NumberFormatException is an exception in java that is thrown
        //when a string cannot be converted into a numeric value
        catch (NumberFormatException e) {
            return false; // Not a valid non-negative number
        }
        return false; // Not a valid non-negative number
    }

    //Validate Category
    public boolean isValidCategory(String category) {
        for (String validCategory : Utility.validCategories) { //Must be one of the valid categories
            if (validCategory.equalsIgnoreCase(category.trim())) {
                return true;
            }
        }
        return false;
    }

    //Validate Price
    public boolean isValidPrice(String price) {
        //Checks to see if it is even a number and an integer
        try {
            double value = Double.parseDouble(price);
            if (value > 0) { //checks to see if is positive
                return true;
            }
        }
        //if the quantity is not even a number it will catch error so the program does not crash
        //NumberFormatException is an exception in java that is thrown
        //when a string cannot be converted into a numeric value
        catch (NumberFormatException e) {
            return false; // Not a valid positive number
        }
        return false;
    }

    //Validate Quantity
    public boolean isValidQuantity(String quantity) {
        //Checks to see if it is even a number and an integer
        try {
            int value = Integer.parseInt(quantity);
            if (value >= 0) { //checks to see if is non-negative
                return true;
            }
        }
        //if the quantity is not even a number it will catch error so the program does not crash
        //NumberFormatException is an exception in java that is thrown
        //when a string cannot be converted into a numeric value
        catch (NumberFormatException e) {
            return false; // Not a valid non-negative number
        }
        return false;
    }
}
