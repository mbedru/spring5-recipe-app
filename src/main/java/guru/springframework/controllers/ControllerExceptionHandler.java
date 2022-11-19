package guru.springframework.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

//this class is to do Exception handling globally//its also possible to do it inside controllers individually, like handleNotFound inside RecipeController.
//but number lemikebelu controllers hulu yihen kemnsera; general yehonutn globally excHandling lemareg, use @Slf4j //@ControllerAdvice
//Remember: MockMvc sensera we should wire the controller advices too, using .setControllerAdvice(new ControllerExceptionHandler())

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception) {
        log.error("Handling NumberFormat exception");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("400error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }

}
