package mobileappdevelopment.kevinholmes.gameoflife;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import static mobileappdevelopment.kevinholmes.gameoflife.CellGridView.mCellRadius;
import static mobileappdevelopment.kevinholmes.gameoflife.CellGridView.mGridSizeX;
import static mobileappdevelopment.kevinholmes.gameoflife.CellGridView.mGridSizeY;
import static mobileappdevelopment.kevinholmes.gameoflife.CellGridView.xAdjust;
import static mobileappdevelopment.kevinholmes.gameoflife.CellGridView.yAdjust;

public class SerializableCellGrid implements Serializable {
    private String mCreationDateTime;
    // x dimension of grid
    private int mWidth;
    // y dimension of grid
    private int mHeight;
    // number of alive cells
    private int mNumAliveCells;
    // internal grid
    private boolean[][] mCellGrid;
    // Created while xAdjust was these values
    public int mCreatedGridSizeX, mCreatedGridSizeY;
    // Preview bitmap for fragments
    BitmapDataObject mPreviewBitmap;

    public long id;

    public SerializableCellGrid() {
        mCreationDateTime = DateFormat.getDateTimeInstance().format(new Date());
        mWidth = 0;
        mHeight = 0;
        mNumAliveCells = 0;
        mCellGrid = new boolean[0][0];
    }

    public SerializableCellGrid(@NonNull boolean[][] cellGrid) {
        if(ValidateCellGrids(cellGrid)) {
            mCellGrid = cellGrid;
            mCreationDateTime = DateFormat.getDateTimeInstance().format(new Date());
            mWidth = cellGrid.length;
            mHeight = cellGrid[0].length;
            mCreatedGridSizeX = mGridSizeX;
            mCreatedGridSizeY = mGridSizeY;
            mNumAliveCells = 0;

            for (boolean[] aCellGrid : cellGrid) {
                for (boolean anACellGrid : aCellGrid) {
                    if(anACellGrid) mNumAliveCells++;
                }
            }
            Bitmap bmp = Bitmap.createBitmap((cellGrid.length * xAdjust) + xAdjust,
                    (cellGrid[0].length * yAdjust) + xAdjust, Bitmap.Config.ARGB_8888);
            Canvas tempCanvas = new Canvas(bmp);
            Paint paint = new Paint();
            paint.setColor(Color.GRAY);
            paint.setAntiAlias(true);
            tempCanvas.drawRect(0, 0, bmp.getWidth(), bmp.getHeight(), paint);
            for(int i = 0; i < cellGrid.length; i++) {
                for(int j = 0; j < cellGrid[0].length; j++) {
                        paint.setColor(mCellGrid[i][j] ? Color.GREEN : Color.BLACK);
                        tempCanvas.drawCircle((i * xAdjust) + xAdjust, (j * yAdjust) + yAdjust, mCellRadius, paint);
                }
            }
            mPreviewBitmap = new BitmapDataObject(bmp);
        } else return;
    }

    private boolean ValidateCellGrids(@NonNull boolean[][] cellGrid) {
        return !((cellGrid.length == 0 || cellGrid[0].length == 0));
    }

    public String getCreationDateTime() {
        return mCreationDateTime;
    }

    public void setCreationDate(String mCreationDateTime) {
        this.mCreationDateTime = mCreationDateTime;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public int getNumAliveCells() {
        return mNumAliveCells;
    }

    public void setNumAliveCells(int mNumAliveCells) {
        this.mNumAliveCells = mNumAliveCells;
    }

    public boolean[][] getCellGrid() {
        return mCellGrid;
    }

    public void setCellGrid(boolean[][] mCellGrid) {
        this.mCellGrid = mCellGrid;
    }
}
