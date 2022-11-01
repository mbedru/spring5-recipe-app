package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized// lombok.Synchronized b/c spring doesn't guarantee threadSafety; thenWeCanRunItOnMultiThreadedEnvironment.
    @Nullable
    @Override
    public CategoryCommand convert(@Nullable Category source) {
        if (source == null) {
            return null;
        }

        final CategoryCommand categoryCommand = new CategoryCommand();//final(makesthemImmutable)forSecurity.

        categoryCommand.setId(source.getId());
        categoryCommand.setDescription(source.getDescription());

        return categoryCommand;
    }
}