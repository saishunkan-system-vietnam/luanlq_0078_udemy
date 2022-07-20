import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:get/get.dart';

import '../../../controllers/home_controller.dart';
import '../widgets/input_text.dart';

class BodyFormInput extends StatelessWidget {
  const BodyFormInput({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: MediaQuery.of(context).viewInsets,
      child: GetBuilder<HomeController>(builder: (_) {
        return FractionallySizedBox(
          heightFactor: 0.53,
          child: SingleChildScrollView(
            child: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 20),
              child: Column(
                children: [
                  InputText(controller: _.inputTitle, labelText: "Title"),
                  const SizedBox(height: 20),
                  InputText(
                      controller: _.inputAmount,
                      textInputType: TextInputType.number,
                      inputFormatters: [FilteringTextInputFormatter.allow(RegExp(r'[0-9.]'))],
                      labelText: "Amount"),
                  const SizedBox(height: 20),
                  InputText(
                    readOnly: true,
                    controller: _.inputDate,
                    labelText: "Transaction time",
                    hintText: "yyyy-MM-dd",
                    icons: IconButton(
                        onPressed: () => _.getDateTime(),
                        icon:
                            const Icon(Icons.date_range, color: Colors.purple)),
                  ),
                  const SizedBox(height: 20),
                  SizedBox(
                    height: 45,
                    child: ElevatedButton(
                        onPressed: () => _.submit(),
                        child: const Text("SUBMIT")),
                  )
                ],
              ),
            ),
          ),
        );
      }),
    );
  }
}
