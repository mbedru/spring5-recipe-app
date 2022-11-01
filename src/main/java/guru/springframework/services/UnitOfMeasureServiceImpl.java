package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by jt on 6/28/17.
 */
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        //the StreamSupport ... wekeba is to change Iterable(findAll()-value) TO Stream.
        return StreamSupport.stream(unitOfMeasureRepository.findAll()//the parallel=false//meansIts Sequential
                        .spliterator(), false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }
}

////////////////////////////////************************///////////////////////////////////////////
/*
1. Parallel Stream :- use only ONE Thread/Core => returns the Collection with preserved
                    - NotMuchSpeed b/c of only one thread

2. Sequential Stream :- uses many Threads/Cores if available
                      - very FAST b/c of many threads
                      - works/depends if both sender/receiver support multi-Threading.*/
