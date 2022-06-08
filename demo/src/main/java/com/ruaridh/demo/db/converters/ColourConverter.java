package com.ruaridh.demo.db.converters;

import com.ruaridh.demo.chess.Piece;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ColourConverter implements AttributeConverter<Piece.Colour, String> {

    @Override
    public String convertToDatabaseColumn(Piece.Colour colour) {
        if (colour == null) {
            return null;
        }
        return colour.toString();
    }

    @Override
    public Piece.Colour convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Piece.Colour.valueOf(dbData);
    }
}
