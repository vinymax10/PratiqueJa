package modelo;

import org.mapstruct.MappingTarget;

public interface BaseMapper<T>
{
	T clone(T source);

	void update(T source, @MappingTarget T target);
}