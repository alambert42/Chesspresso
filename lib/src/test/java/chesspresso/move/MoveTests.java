/*
 * MoveTests.java
 *
 * Created on 7. September 2001, 10:32
 */

package chesspresso.move;

import chesspresso.Chess;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 *
 * @author  BerniMan
 * @version 
 */
public class MoveTests
{

    private void checkMove(short move,
                           int from, int to,
                           boolean isCapturing, boolean isPromo, int promoPiece, boolean isEPMove,
                           boolean isCastle, boolean isShortCastle, boolean isLongCastle,
                           boolean isSpecial, boolean isValid)
    {
        if (!isSpecial) {
            if (!isCastle) {
                assertEquals(from,        Move.getFromSqi(move),"from is wrong " + Move.getString(move));
                assertEquals(to,          Move.getToSqi(move), "to is wrong " + Move.getString(move));
                assertEquals(isCapturing, Move.isCapturing(move), "isCapturing is wrong " + Move.getString(move));
                assertEquals(isPromo,     Move.isPromotion(move), "isPromotion is wrong " + Move.getString(move));
                assertEquals(promoPiece,  Move.getPromotionPiece(move), "promotionPiece is wrong " + Move.getString(move));
                assertEquals(isEPMove,    Move.isEPMove(move), "isEPMove is wrong " + Move.getString(move) );
            }
            assertEquals(isCastle,      Move.isCastle(move), "isCastle is wrong " + Move.getString(move));
            assertEquals(isShortCastle, Move.isShortCastle(move), "isLongCastle is wrong " + Move.getString(move) );
            assertEquals(isLongCastle,  Move.isLongCastle(move), "isShortCastle is wrong " + Move.getString(move));
        }
        assertEquals(isSpecial, Move.isSpecial(move), "isSpecial is wrong " + Move.getString(move));
        assertEquals(isValid  , Move.isValid(move), "isValid is wrong " + Move.getString(move));
    }
    
    @Test
    public void testMove() throws Exception
    {
        for (int from = 0; from < Chess.NUM_OF_SQUARES; from++) {
            for (int to = 0; to < Chess.NUM_OF_SQUARES; to++) {
                for (int capturing=0; capturing <= 1; capturing++) {
                    checkMove(Move.getRegularMove(from, to, capturing == 1),
                              from, to,
                              capturing == 1, false, Chess.NO_PIECE, false,
                              false, false, false,
                              false, true);
                    checkMove(Move.getPawnMove(from, to, capturing == 1, Chess.QUEEN),
                              from, to,
                              capturing == 1, true, Chess.QUEEN, false,
                              false, false, false,
                              false, true);
                    checkMove(Move.getPawnMove(from, to, capturing == 1, Chess.ROOK),
                              from, to,
                              capturing == 1, true, Chess.ROOK, false,
                              false, false, false,
                              false, true);
                    checkMove(Move.getPawnMove(from, to, capturing == 1, Chess.BISHOP),
                              from, to,
                              capturing == 1, true, Chess.BISHOP, false,
                              false, false, false,
                              false, true);
                    checkMove(Move.getPawnMove(from, to, capturing == 1, Chess.KNIGHT),
                              from, to,
                              capturing == 1, true, Chess.KNIGHT, false,
                              false, false, false,
                              false, true);
                }
                checkMove(Move.getEPMove(from, to),
                          from, to,
                          true, false, Chess.NO_PIECE, true,
                          false, false, false,
                          false, true);
            }
        }
        
        checkMove(Move.WHITE_SHORT_CASTLE,
                  Chess.E1, Chess.G1,
                  false, false, Chess.NO_PIECE, false,
                  true, true, false,
                  false, true);
        checkMove(Move.WHITE_LONG_CASTLE,
                  Chess.E1, Chess.C1,
                  false, false, Chess.NO_PIECE, false,
                  true, false, true,
                  false, true);
        checkMove(Move.BLACK_SHORT_CASTLE,
                  Chess.E8, Chess.G8,
                  false, false, Chess.NO_PIECE, false,
                  true, true, false,
                  false, true);
        checkMove(Move.BLACK_LONG_CASTLE,
                  Chess.E8, Chess.C8,
                  false, false, Chess.NO_PIECE, false,
                  true, false, true,
                  false, true);
        checkMove(Move.NO_MOVE,
                  0, 0,
                  false, false, Chess.NO_PIECE, false,
                  false, false, false,
                  true, false);
        checkMove(Move.ILLEGAL_MOVE,
                  0, 0,
                  false, false, Chess.NO_PIECE, false,
                  false, false, false,
                  true, false);
    }

}