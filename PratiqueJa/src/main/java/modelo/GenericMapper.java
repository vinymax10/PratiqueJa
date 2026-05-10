package modelo;


import org.modelmapper.ModelMapper;

public class GenericMapper<T> implements BaseMapper<T> {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public T clone(T source) {
        try {
            T target = (T) source.getClass().getDeclaredConstructor().newInstance();
            modelMapper.map(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao clonar objeto", e);
        }
    }

    @Override
    public void update(T source, T target) {
        modelMapper.map(source, target);
    }
}