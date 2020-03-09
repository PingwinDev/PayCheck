package com.cusyas.android.paycheck.BillDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BillDao {

    @Query("SELECT * FROM bill_table ORDER BY bill_name ASC")
    fun getAlphabetizedBills(): LiveData<List<Bill>>

    @Query("SELECT * FROM bill_table WHERE id IN (:billIds)")
    fun loadAllByIds(billIds: IntArray): List<Bill>

    @Query("SELECT * FROM BILL_TABLE WHERE id IS :billId")
    fun loadById(billId: Int): LiveData<Bill>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bill: Bill)

    @Delete
    fun delete(bill: Bill)
}