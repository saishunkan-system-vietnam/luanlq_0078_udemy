import 'package:flutter/material.dart';

import 'package:get/get.dart';

import '../controllers/home_controller.dart';
import 'components/bar_chart/chart.dart';
import 'components/list/item_list.dart';

class HomeView extends GetView<HomeController> {
  @override
  Widget build(BuildContext context) {
    return GetBuilder<HomeController>(builder: (_) {
      return Scaffold(
        body: Column(
          children: [
            Container(
                height: 300,
                margin: EdgeInsets.only(top: 20),
                child: Chart(listTransaction: _.listTransaction)),
           Divider(thickness: 2),
            Expanded(
              child: ListView.builder(
                  shrinkWrap: true,
                  itemCount: _.listTransaction.length,
                  itemBuilder: (context, index) {
                    return ItemList(
                        deleteTx: () {
                          _.listTransaction.removeAt(index);
                          _.update();
                        },
                        title: _.listTransaction[index].title!,
                        date: _.listTransaction[index].date!,
                        amount: _.listTransaction[index].amount!);
                  }),
            )
          ],
        ),
        floatingActionButton: _.isHideFloatBtn.value
            ? SizedBox.shrink()
            : FloatingActionButton(
          onPressed: () => _.handleShowFormInput(),
          child: Icon(Icons.add, color: Colors.black54),
        ),
        floatingActionButtonLocation:
        FloatingActionButtonLocation.centerFloat,
      );
    });
  }
}
