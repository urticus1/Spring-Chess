package com.ruaridh.demo.db.converters;

import com.ruaridh.demo.GameFactory;
import com.ruaridh.demo.chess.Board;
import com.ruaridh.demo.chess.InvalidFENException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BoardConverter implements AttributeConverter<Board, String> {

    @Override
    public String convertToDatabaseColumn(Board board) {
        if (board == null) {
            return null;
        }
        return board.toFEN();
    }

    @Override
    public Board convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return GameFactory.loadFromFEN(dbData);
        }
        catch (InvalidFENException e) {
            return null; //lets not put invalid games in db?
        }
    }
}
