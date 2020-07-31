package rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
import java.math.BigInteger;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FactorialController {
    static class RestResponse {
        int number;
        BigInteger result;

        public RestResponse() {}

        public BigInteger getResult() {
            return result;
        }

        public void setResult(BigInteger result) {
            this.result = result;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

    @GetMapping("/factorial/{number}")
    public RestResponse factorial(@PathVariable(name = "number") Integer number) {
        RestResponse restResponse = new RestResponse();

        try {

            if (number == 0 | number == 1) {
                BigInteger intNumber = BigInteger.ONE;
                restResponse.setNumber(number);
                restResponse.setResult(intNumber);
            } else {

                BigInteger intNumber = BigInteger.ONE;
                for (int i = 1; i <= number - 1; ++i) {
                    intNumber = intNumber.add(factorial(BigInteger.valueOf(i), i));
                    restResponse.setNumber(number);
                    restResponse.setResult(intNumber);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return restResponse;
    }

    private static BigInteger factorial(BigInteger result, int number) {
        while (number > 0) {
            BigInteger rs = BigInteger.valueOf(number);
            result = result.multiply(rs);
            number --;
        }
        return result;
    }
}
