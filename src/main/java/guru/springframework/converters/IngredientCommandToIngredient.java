package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


/**
 * Created by jt on 6/21/17.
 */
@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized//I added this... b/c i thought he forget to add this//BUT SHE said we only use it for multithreadedFns.
    @Nullable
    @Override
    public Ingredient convert(@Nullable IngredientCommand source) {//@Nullable is addedBy-Me.
        if (source == null) {
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());

        if(source.getRecipeId() != null){
            Recipe recipe = new Recipe();
            recipe.setId(source.getRecipeId());//insteadofMakingnewRecipe, whydontweFindById the existing recipe?
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);//ifweUsing addIngredient, itllAlsoDo:ingredient.setRecipe(recipe); so the the line above this is not necessary.
        }

        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(uomConverter.convert(source.getUom()));
        return ingredient;
    }
}