import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

import '../../../../../models/transaction.dart';
import 'chart_bar.dart';

class Chart extends StatelessWidget {
  const Chart({Key? key, required this.listTransaction}) : super(key: key);

  final List<Transaction> listTransaction;

  List<Map<String, dynamic>> get listDataTransaction {
    return List.generate(7, (index) {
      final weekDay = DateTime.now().subtract(Duration(days: index));
      var sumTotal = 0.0;
      for (var item in listTransaction) {
        if (item.date!.day == weekDay.day &&
            item.date!.month == weekDay.month &&
            item.date!.year == weekDay.year) {
          sumTotal += item.amount!;
        }
      }
      return {
        'day': DateFormat.E().format(weekDay).substring(0, 1),
        'amount': sumTotal,
      };
    }).reversed.toList();
  }

  double get totalSpending {
    return listDataTransaction.fold(0.0, (sum, item) {
      return sum + item['amount'];
    });
  }

  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 6,
      margin: EdgeInsets.all(20),
      child: Padding(
        padding: EdgeInsets.all(10),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: listDataTransaction.map((data) {
            return Flexible(
              fit: FlexFit.tight,
              child: ChartBar(
                  spendingPctOfTotal: totalSpending == 0.0
                      ? 0.0
                      : (data['amount'] as double) / totalSpending,
                  spendingAmount: data['amount'],
                  label: data['day']),
            );
          }).toList(),
        ),
      ),
    );
  }
}
