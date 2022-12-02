package portfolio2022.portfolio2022.dailyshop.exception;

public class NotEnoughStockException extends RuntimeException{
    public NotEnoughStockException(){
        super();
    }

    public NotEnoughStockException(String message){
        super(message);
    }
    public NotEnoughStockException(String message,Throwable cause){
        super(message,cause);
    }
    public NotEnoughStockException(Throwable cause){
        super(cause);
    }
}
