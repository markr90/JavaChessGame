package GameDisplay;

import Pieces.IPiece;

public interface PawnPromotionListener {
    IPiece promptPromotion(boolean isWhite);
}
